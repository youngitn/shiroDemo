package study.young.app.shiro;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import study.young.app.entity.User;

public class ShiroDBRealm extends AuthorizingRealm {

	final String username = "admin";// 用戶名
	final String password = "d9be323985a51535f6f6c55750f23af5";// 用戶密碼，使用123與wx加密得到

	/**
	 * 該方法身份認證
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		// 獲取身份
		String username = (String) authenticationToken.getPrincipal();
		// 模擬數據庫查詢
		User user = queryUserByName(username);
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getName(), user.getPassword(), // 密碼
				ByteSource.Util.bytes(user.getSalt()), getName());
		return authenticationInfo;
	}

	/**
	 * 用戶授權認證 調用時機，在使用Subject中的權限角色驗證時，如checkPermission等
	 */

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		Set<String> roles = new HashSet<>();// 角色集合
		Set<String> set = new HashSet<String>();// 權限集合
		set.add("test");// 添加權限
		set.add("create");// 添加權限
		info.setStringPermissions(set);
		System.out.print("權限添加成功");
		return info;
	}

	/**
	 * 獲取用戶信息
	 * 
	 * @param name
	 * @return
	 */
	public User queryUserByName(String name) {
		User user = new User();
		user.setSalt("wx");
		user.setId("1");
		user.setName(username);
		user.setPassword(password);
		return user;
	}
}
