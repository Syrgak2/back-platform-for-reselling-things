package ru.skypro.homework.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.auth.NewPasswordDTO;
import ru.skypro.homework.dto.user.UpdateUserDTO;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.model.Photo;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.PhotoService;

import java.io.IOException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PhotoService photoService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void save() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPass");

        when(userRepository.save(user)).thenReturn(user);
        User savedUser = userService.save(user);

        verify(userRepository).save(user);
        assertEquals(user.getUsername(), savedUser.getUsername());
        assertEquals(user.getPassword(), savedUser.getPassword());
    }

    @Test
    void findById() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setUsername("testUser");
        user.setPassword("testPass");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        User foundUser = userService.find(userId);

        verify(userRepository).findById(userId);
        assertNotNull(foundUser);
        assertEquals(userId, foundUser.getId());
    }

    @Test
    public void findByIdWhenUserDoesNotExist() {
        Long userId = 2L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        User foundUser = userService.find(userId);

        verify(userRepository).findById(userId);
        assertNull(foundUser);
    }

    @Test
    void findByName() {
        String username = "existingUser";
        User mockUser = new User();
        mockUser.setUsername(username);
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(mockUser));

        User found = userService.find(username);

        assertNotNull(found);
        assertEquals(username, found.getUsername());
    }
    @Test
    void findByNameWithNonExistingUsername() {
        String username = "nonExistingUser";
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        User found = userService.find(username);

        assertNull(found);
    }

    @Test
    void findByCommentId() {
        Long commentId = 1L;
        User mockUser = new User();
        mockUser.setId(commentId);
        when(userRepository.findByCommentId(commentId)).thenReturn(Optional.of(mockUser));

        User found = userService.findByCommentId(commentId);

        assertNotNull(found);
        assertEquals(commentId, found.getId());
    }

    @Test
    void FindByCommentIdWithNonExisting() {
        Long commentId = 2L;
        when(userRepository.findByCommentId(commentId)).thenReturn(Optional.empty());

        User found = userService.findByCommentId(commentId);

        assertNull(found);
    }

    @Test
    // тест проверяет, что метод saveAvatar корректно работает, когда пользователь существует, но у него ещё нет аватара
    void saveAvatar() throws IOException {
        String userName = "existingUser";
        User mockUser = new User();
        mockUser.setUsername(userName);
        mockUser.setUserAvatar(null);

        MultipartFile file = new MockMultipartFile("file", "test.png", "image/png", "test image content".getBytes());

        Photo savedPhoto = new Photo();
        savedPhoto.setId(1L);

        when(userRepository.findByUsername(userName)).thenReturn(Optional.of(mockUser));
        when(photoService.save(file)).thenReturn(savedPhoto);

        // Выполнение
        Boolean result = userService.saveAvatar(file, userName);

        // Проверка
        assertTrue(result);
        assertNotNull(mockUser.getUserAvatar());
        assertEquals("/images?imageId=" + savedPhoto.getId(), mockUser.getImageUrl());
    }

    @Test
    // тест проверяет поведение метода saveAvatar, когда у существующего пользователя уже есть аватар, и он хочет заменить его на новый
    void testSaveAvatarWithExistingUserAndReplacingAvatar() throws IOException {
        String userName = "existingUser";
        User mockUser = new User();
        mockUser.setUsername(userName);

        Photo existingPhoto = new Photo();
        existingPhoto.setId(1L);
        mockUser.setUserAvatar(existingPhoto);

        MultipartFile file = new MockMultipartFile("file", "test.png", "image/png", "test image content".getBytes());

        Photo newSavedPhoto = new Photo();
        newSavedPhoto.setId(2L);

        when(userRepository.findByUsername(userName)).thenReturn(Optional.of(mockUser));
        when(photoService.remove(existingPhoto.getId())).thenReturn(true); // Используйте when().thenReturn() для не-void методов
        when(photoService.save(file)).thenReturn(newSavedPhoto);

        // Выполнение
        Boolean result = userService.saveAvatar(file, userName);

        // Проверка
        assertTrue(result);
        assertNotNull(mockUser.getUserAvatar());
        assertEquals("/images?imageId=" + newSavedPhoto.getId(), mockUser.getImageUrl());
        verify(photoService).remove(existingPhoto.getId());
    }

    @Test
    // тест проверяет, что метод saveAvatar выбрасывает исключение NotFoundException, когда пытается сохранить аватар для несуществующего пользователя
    void testSaveAvatarWithNonExistingUser() {
        String userName = "nonExistingUser";
        MultipartFile file = new MockMultipartFile("file", "test.png", "image/png", "test image content".getBytes());

        when(userRepository.findByUsername(userName)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            userService.saveAvatar(file, userName);
        });
    }

    @Test
    void edite() {
        String username = "testUser";
        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        updateUserDTO.setFirstName("NewFirstName");
        updateUserDTO.setLastName("NewLastName");
        updateUserDTO.setPhone("1234567890");

        User foundUser = new User();
        foundUser.setUsername(username);
        foundUser.setFirstName("OldFirstName");
        foundUser.setLastName("OldLastName");
        foundUser.setPhone("0987654321");

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(foundUser));
        when(userRepository.save(any(User.class))).thenReturn(foundUser);

        User editedUser = userService.edite(username, updateUserDTO);

        assertNotNull(editedUser, "Edited user should not be null");
        assertEquals(updateUserDTO.getFirstName(), editedUser.getFirstName(), "First name should be updated");
        assertEquals(updateUserDTO.getLastName(), editedUser.getLastName(), "Last name should be updated");
        assertEquals(updateUserDTO.getPhone(), editedUser.getPhone(), "Phone should be updated");

        verify(userRepository).findByUsername(username);
        verify(userRepository).save(any(User.class));

    }

    //@Test
    //void setPasswordSuccess() {
    //    String existingPassword = "hashedCurrentPassword";
    //    String newPassword = "newPassword";
    //    User user = new User();
    //    user.setPassword(existingPassword);
    //    NewPasswordDTO newPasswordDTO = new NewPasswordDTO();
    //    newPasswordDTO.setCurrentPassword("currentPassword");
    //    newPasswordDTO.setNewPassword(newPassword);
    //
    //    when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
    //    when(passwordEncoder.matches(anyString(), eq(existingPassword))).thenReturn(true);
    //    when(passwordEncoder.encode(newPassword)).thenReturn("hashedNewPassword");
    //
    //    boolean result = userService.setPassword("testUser", newPasswordDTO);
    //
    //    assertTrue(result, "Password should be updated successfully");
    //    verify(userRepository).save(user);
    //    verify(passwordEncoder).encode(newPassword);
    //}

    @Test
    void setPasswordFailure() {
        String name = "userTest";
        User user = new User();
        user.setPassword(passwordEncoder.encode("currentPassword"));
        NewPasswordDTO newPasswordDTO = new NewPasswordDTO();
        newPasswordDTO.setCurrentPassword("wrongCurrentPassword");
        newPasswordDTO.setNewPassword("newPassword");

        when(userRepository.findByUsername(name)).thenReturn(Optional.of(user));

        boolean result = userService.setPassword(name, newPasswordDTO);

        assertFalse(result, "Password should not be updated with wrong current password");
        verify(userRepository, never()).save(any());
    }
}