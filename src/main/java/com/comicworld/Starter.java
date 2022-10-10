//package com.comicworld;
//
//import com.comicworld.layer.domain.entity.Authority;
//import com.comicworld.layer.domain.entity.account.AdminAccount;
//import com.comicworld.layer.domain.entity.profile.AdminProfile;
//import com.comicworld.layer.domain.service.admin.account.AdminAccountService;
//import com.comicworld.layer.domain.service.admin.profile.AdminProfileService;
//import com.comicworld.layer.domain.service.authority.AuthorityService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//
//@Component
//public class Starter implements CommandLineRunner {
//
//    @Autowired
//    @Qualifier("authorityServiceImplV1")
//    private AuthorityService authorityService;
//
//    @Autowired
//    @Qualifier("adminAccountServiceImplV1")
//    private AdminAccountService adminAccountService;
//
//    @Autowired
//    @Qualifier("adminProfileServiceImplV1")
//    private AdminProfileService adminProfileService;
//
//    @Autowired
//    private PasswordEncoder adminPasswordEncoder;
//
//    @Override
//    public void run(String... args) throws Exception {
////        Authority user = new Authority();
////        user.setRole("ROLE_USER");
////
////        Authority admin = new Authority();
////        admin.setRole("ROLE_ADMIN");
////
////        this.authorityService.saveOrUpdateAll(Arrays.asList(user, admin));
////
////        AdminAccount adminAccount = new AdminAccount();
////        adminAccount.setAccountType(0);
////        adminAccount.setUsername("deptraikhoaito");
////        adminAccount.setPassword(this.adminPasswordEncoder.encode("admin"));
////        adminAccount.setAccountNonExpired(true);
////        adminAccount.setAccountNonLocked(true);
////        adminAccount.setCredentialsNonExpired(true);
////        adminAccount.setEnabled(true);
////
////        adminAccount = this.adminAccountService.saveOrUpdate(adminAccount);
////
////        AdminProfile profile = new AdminProfile();
////        profile.setAccount(adminAccount);
////        profile.setFullName("Nguyễn Trọng Cương");
////        profile.setUsername("deptraikhoaito");
////        profile.setAvatarLink("https://comicworld.s3.ap-southeast-1.amazonaws.com/admin/default_gender.png");
////
////        this.adminProfileService.saveOrUpdate(profile);
//
//        AdminAccount account = this.adminAccountService.findByIdWithAuthoritiesLoadedEagerly(3l).get();
//
//        Authority admin = this.authorityService.findByIdWithAdminAccountsLoadedEagerly(2l).get();
//
//        account.getAuthorities().add(admin);
//
//        admin.getAdminAccounts().add(account);
//
//        this.authorityService.saveOrUpdate(admin);
//
//        this.adminAccountService.saveOrUpdate(account);
//
//    }
//
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
