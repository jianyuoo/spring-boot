package air.admin.spring_boot.base.permissions;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class PermissionValidator {
    /**
     * 检查当前用户是否具有指定权限
     *
     * @param permission 权限标识
     * @return 是否具有权限
     */
    public boolean hasPermission(String permission) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return false; // 未认证用户不具有任何权限
        }

        // 检查用户的权限（这部分可以根据你实际的用户角色和权限管理方式实现）
        return authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(permission));
    }
}
