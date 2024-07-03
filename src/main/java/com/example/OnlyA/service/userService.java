package com.example.OnlyA.service;

import com.example.OnlyA.config.Role;
import com.example.OnlyA.model.Recruiter;
import com.example.OnlyA.model.User;
import com.example.OnlyA.repository.roleRepo;
import com.example.OnlyA.repository.userRepo;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class userService implements UserDetailsService {
    @Autowired
    private userRepo userRepository;
    @Autowired
    private roleRepo roleRepository;

    // Lưu người dùng mới vào cơ sở dữ liệu sau khi mã hóa mật khẩu.
    public void save(@NotNull User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }

    // Gán vai trò mặc định cho người dùng dựa trên tên người dùng.
    public void setDefaultRole(String username, String accountType) {
        userRepository.findByUsername(username).ifPresentOrElse(
                user -> {
                    if ("personal".equalsIgnoreCase(accountType)) {
                        user.getRoles().add(roleRepository.findRoleById(Role.PERSONAL.value));
                    } else if ("business".equalsIgnoreCase(accountType)) {
                        user.getRoles().add(roleRepository.findRoleById(Role.BUSINESS.value));
                    }
                    userRepository.save(user);
                },
                () -> {
                    throw new UsernameNotFoundException("User not found");
                }
        );
    }

    // Tải thông tin chi tiết người dùng để xác thực.
    @Override
    public UserDetails loadUserByUsername(String username) throws
            UsernameNotFoundException {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getAuthorities())
                .accountExpired(!user.isAccountNonExpired())
                .accountLocked(!user.isAccountNonLocked())
                .credentialsExpired(!user.isCredentialsNonExpired())
                .disabled(!user.isEnabled())
                .build();
    }

    // Tìm kiếm người dùng dựa trên tên đăng nhập.
    public Optional<User> findByUsername(String username) throws
            UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public User timtheousername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).get();
    }

    public String getLoggedInUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    public String getLoggedInUserId() {
        String username = getLoggedInUsername();
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return user.get().getUserID();
        }
        return null; // Hoặc bạn có thể ném một ngoại lệ nếu không tìm thấy user
    }

    public int userHasCompany(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.get().getHasCompany();
    }

    public void updateHasCompany(@NotNull User user) {
        User existinguser = userRepository.findById(user.getUserID())
                .orElseThrow(() -> new IllegalStateException("Company with ID " + user.getUserID() + " does not exist."));
        existinguser.setHasCompany(user.getHasCompany());

        userRepository.save(existinguser);
    }
}