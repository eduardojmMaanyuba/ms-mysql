package com.maanyuba.msmysql.service;

import com.maanyuba.msmysql.dto.GenericResponse;
import com.maanyuba.msmysql.dto.UserDto;
import com.maanyuba.msmysql.entity.User;
import com.maanyuba.msmysql.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public GenericResponse createUser(UserDto userDto) {
        try {
            if (userDto.getPhone() == null || userDto.getPhone().isEmpty()) {
                throw new RuntimeException("Phone is required");
            }
            if (userDto.getBornDate() == null) {
                throw new RuntimeException("Born Date is required");
            }
            if (userDto.getFatherLastName() == null || userDto.getFatherLastName().isEmpty()) {
                throw new RuntimeException("Father Last Name is required");
            }
            if (userDto.getMotherLastName() == null || userDto.getMotherLastName().isEmpty()) {
                throw new RuntimeException("Mother Last Name is required");
            }
            if (userDto.getName() == null || userDto.getName().isEmpty()) {
                throw new RuntimeException("Name is required");
            }
            if (userDto.getMail() == null || userDto.getMail().isEmpty()) {
                throw new RuntimeException("Mail is required");
            }
            if (userDto.getPhone().length() != 10) {
                throw new RuntimeException("Phone lenght is wrong");
            }
            User user = User.builder()
                    .phone(userDto.getPhone())
                    .bornDate(userDto.getBornDate())
                    .fatherLastName(userDto.getFatherLastName())
                    .motherLastName(userDto.getMotherLastName())
                    .name(userDto.getName())
                    .mail(userDto.getMail())
                    .build();
            user = userRepository.save(user);
            userDto.setId(user.getId());
            return GenericResponse.builder()
                    .data(userDto)
                    .bussinessMeaning("Ok.")
                    .status(true)
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return GenericResponse.builder()
                    .bussinessMeaning(e.getMessage())
                    .status(false)
                    .build();
        }
    }

    public GenericResponse getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            return GenericResponse.builder()
                    .bussinessMeaning("Users not found.")
                    .status(false)
                    .build();
        } else {
            List<UserDto> userDtos = new ArrayList<>();
            for (User user: users) {
                UserDto userDto = UserDto.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .fatherLastName(user.getFatherLastName())
                        .motherLastName(user.getMotherLastName())
                        .bornDate(user.getBornDate())
                        .mail(user.getMail())
                        .phone(user.getPhone())
                        .build();
                userDtos.add(userDto);
            }
            return GenericResponse.builder()
                    .data(userDtos)
                    .bussinessMeaning("Ok.")
                    .status(true)
                    .build();
        }
    }

    public GenericResponse getUser(int id) {
        try {
            User user = userRepository.findById(id).orElseThrow(
                    () -> new RuntimeException("User not found."));
            UserDto userDto = UserDto.builder()
                    .phone(user.getPhone())
                    .mail(user.getMail())
                    .name(user.getName())
                    .fatherLastName(user.getFatherLastName())
                    .motherLastName(user.getMotherLastName())
                    .id(user.getId())
                    .build();
            return GenericResponse.builder()
                    .data(userDto)
                    .bussinessMeaning("Ok.")
                    .status(true)
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return GenericResponse.builder()
                    .bussinessMeaning(e.getMessage())
                    .status(false)
                    .build();
        }
    }
    public GenericResponse deleteUser(int id) {
        try {
            userRepository.deleteById(id);
            return GenericResponse.builder()
                    .bussinessMeaning("Ok.")
                    .status(true)
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return GenericResponse.builder()
                    .bussinessMeaning(e.getMessage())
                    .status(false)
                    .build();
        }
    }
}
