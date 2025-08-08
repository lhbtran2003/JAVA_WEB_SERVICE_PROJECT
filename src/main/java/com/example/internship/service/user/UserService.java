package com.example.internship.service.user;

import com.example.internship.config.exception.ConflictDataException;
import com.example.internship.config.exception.NotFoundException;
import com.example.internship.config.security.jwt.JwtProvider;
import com.example.internship.config.security.principle.UserDetail;
import com.example.internship.dto.request.user.AddUserRequest;
import com.example.internship.dto.request.FormLogin;
import com.example.internship.dto.request.user.UpdateIsActiveRequest;
import com.example.internship.dto.request.user.UpdateRoleRequest;
import com.example.internship.dto.request.user.UpdateUserRequest;
import com.example.internship.dto.response.ApiResponse;
import com.example.internship.entity.RoleName;
import com.example.internship.entity.User;
import com.example.internship.mapper.RoleNameMapper;
import com.example.internship.mapper.UserMapper;
import com.example.internship.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ApiResponse<String> login(FormLogin request) {
        Authentication auth = new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        );
        Authentication authenticate = authenticationManager.authenticate(auth);
        String accessToken = jwtProvider.generateAccessToken((UserDetail) authenticate.getPrincipal());

        return ApiResponse.<String>builder()
                .success(true)
                .data(accessToken)
                .build();
    }

    @Override
    public ApiResponse<User> getUserInformation() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            Object principal = auth.getPrincipal();

            if (principal instanceof UserDetail userDetail) {
                User user = userRepository.findByUsername(userDetail.getUsername()).get();
                return ApiResponse.<User>builder()
                        .success(true)
                        .message("Lấy thông tin tài khoản thành công")
                        .data(user)
                        .timestamp(LocalDateTime.now())
                        .build();
            }
        }
        return null;
    }

    @Override
    public ApiResponse<List<User>> getAllUsers(String role) {
        RoleName roleName = RoleNameMapper.mapRoleToRoleName(role);
        if (roleName != null) {
            return ApiResponse.<List<User>>builder()
                    .success(true)
                    .message("Lấy danh sách người dùng thành công")
                    .data(userRepository.findByRole(roleName))
                    .build();
        }
        return ApiResponse.<List<User>>builder()
                .success(true)
                .message("Lấy danh sách người dùng thành công")
                .data(userRepository.findAll())
                .build();
    }

    @Override
    public ApiResponse<User> getUserById(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("Không tìm thấy tài nguyên user có id " + id);
        }
        return ApiResponse.<User>builder()
                .success(true)
                .message("Lấy thông tin người dùng theo id thành công")
                .data(userRepository.findById(id).orElse(null))
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Override
    public ApiResponse<User> createUser(AddUserRequest request) {
        Long count  = userRepository.countByEmail(request.getEmail());
        if (count > 0) {
            throw new ConflictDataException("Email đã được sử dụng");
        }
        User user = UserMapper.toEntity(request,passwordEncoder);
        return ApiResponse.<User>builder()
                .success(true)
                .message("Tạo mới tài khoản thành công")
                .data(userRepository.save(user))
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Override
    public ApiResponse<User> updateUser(Integer id, UpdateUserRequest request) {
        User userExist = userRepository.findById(id).orElseThrow(() -> new NotFoundException("Không tìm thấy user có id " + id));
        Long count = userRepository.countByEmailExcludeUserId(request.getEmail(), id);
        if (count > 0) {
            throw new ConflictDataException("Email đã được sử dụng");
        }
        User user = UserMapper.toEntity(request, userExist,passwordEncoder);
        return ApiResponse.<User>builder()
                .success(true)
                .message("Cập nhật thông tin cơ bản thành công")
                .data(userRepository.save(user))
                .build();
    }

    @Override
    public ApiResponse<User> changeStatus(Integer id, UpdateIsActiveRequest request) {
        User userExist = userRepository.findById(id).orElseThrow(() -> new NotFoundException("Không tìm thấy user có id " + id));
        userExist.setActive(request.isActive());
        return ApiResponse.<User>builder()
                .success(true)
                .message("Thay đổi trạng thái người dùng thành công")
                .data(userExist)
                .build();
    }

    @Override
    public ApiResponse<User> changeRole(Integer id, UpdateRoleRequest role) {
        User userExist = userRepository.findById(id).orElseThrow(() -> new NotFoundException("Không tìm thấy user có id " + id));
        RoleName roleName = RoleNameMapper.mapRoleToRoleName(role.getRole());
        userExist.setRole(roleName);
        return ApiResponse.<User>builder()
                .success(true)
                .message("Thay đổi vai trò người dùng thành công")
                .data(userExist)
                .build();
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }


}
