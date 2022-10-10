package com.comicworld;

import com.comicworld.layer.domain.entity.Author;
import com.comicworld.layer.domain.entity.Authority;
import com.comicworld.layer.domain.entity.Category;
import com.comicworld.layer.domain.entity.account.AdminAccount;
import com.comicworld.layer.domain.entity.chapter.Source;
import com.comicworld.layer.domain.entity.profile.AdminProfile;
import com.comicworld.layer.domain.service.admin.account.AdminAccountService;
import com.comicworld.layer.domain.service.admin.profile.AdminProfileService;
import com.comicworld.layer.domain.service.alternative_name.crud.AlternativeNameService;
import com.comicworld.layer.domain.service.author.crud.AuthorService;
import com.comicworld.layer.domain.service.authority.AuthorityService;
import com.comicworld.layer.domain.service.category.crud.CategoryService;
import com.comicworld.layer.domain.service.source.SourceService;
import com.comicworld.layer.domain.service.translator_group.crud.TranslatorGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class TestController {

    @Autowired
    @Qualifier("authorityServiceImplV1")
    private AuthorityService authorityService;

    @Autowired
    @Qualifier("adminAccountServiceImplV1")
    private AdminAccountService adminAccountService;

    @Autowired
    @Qualifier("adminProfileServiceImplV1")
    private AdminProfileService adminProfileService;

    @Autowired
    @Qualifier("translatorGroupServiceImplV1")
    private TranslatorGroupService translatorGroupService;

    @Autowired
    @Qualifier("authorServiceImplV1")
    private AuthorService authorService;

    @Autowired
    @Qualifier("categoryServiceImplV1")
    private CategoryService categoryService;

    @Autowired
    @Qualifier("alternativeNameServiceImplV1")
    private AlternativeNameService alternativeNameService;

    @Autowired
    @Qualifier("sourceServiceImplV1")
    private SourceService sourceService;

    @Autowired
    private PasswordEncoder adminPasswordEncoder;

    @GetMapping(path = "/")
    public String test() {
        return "Cuong Dep Trai HIHI";
    }

    @GetMapping(path = "/first")
    public String populateData1() {
        Authority user = new Authority();
        user.setRole("ROLE_USER");

        Authority admin = new Authority();
        admin.setRole("ROLE_ADMIN");

        this.authorityService.saveOrUpdateAll(Arrays.asList(user, admin));
//
        AdminAccount adminAccount = new AdminAccount();
        adminAccount.setAccountType(0);
        adminAccount.setUsername("deptraikhoaito");
        adminAccount.setPassword(this.adminPasswordEncoder.encode("admin"));
        adminAccount.setAccountNonExpired(true);
        adminAccount.setAccountNonLocked(true);
        adminAccount.setCredentialsNonExpired(true);
        adminAccount.setEnabled(true);

        adminAccount = this.adminAccountService.saveOrUpdate(adminAccount);

        AdminProfile profile = new AdminProfile();
        profile.setAccount(adminAccount);
        profile.setFullName("Nguyễn Minh Thành");
        profile.setUsername("deptraikhoaito");
        profile.setAvatarLink("https://comicworld.s3.ap-southeast-1.amazonaws.com/admin/default_gender.png");

        this.adminProfileService.saveOrUpdate(profile);

        AdminAccount adminAccount2 = new AdminAccount();
        adminAccount2.setAccountType(0);
        adminAccount2.setUsername("deptraithongminh");
        adminAccount2.setPassword(this.adminPasswordEncoder.encode("admin"));
        adminAccount2.setAccountNonExpired(true);
        adminAccount2.setAccountNonLocked(true);
        adminAccount2.setCredentialsNonExpired(true);
        adminAccount2.setEnabled(true);

        adminAccount2 = this.adminAccountService.saveOrUpdate(adminAccount2);

        AdminProfile profile2 = new AdminProfile();
        profile2.setAccount(adminAccount2);
        profile2.setFullName("Bui Ba Tien");
        profile2.setUsername("deptraithongminh");
        profile2.setAvatarLink("https://comicworld.s3.ap-southeast-1.amazonaws.com/admin/default_gender.png");

        this.adminProfileService.saveOrUpdate(profile2);

//        String regex = "[^\\p{L}\\p{N}\\p{P}\\p{Z}]";
//
//        Pattern pattern = Pattern.compile(
//                regex,
//                Pattern.UNICODE_CHARACTER_CLASS);
//
//        return pattern.matcher("Đẹp trải mạnh bạo cá cơm").replaceAll("");

        String str = "ABC";

//        str = str.replaceAll("à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ|ặ|ẳ|ẵ", "a");
//        str = str.replaceAll("è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ", "e");
//        str = str.replaceAll("ì|í|ị|ỉ|ĩ", "i");
//        str = str.replaceAll("ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ|ợ|ở|ỡ", "o");
//        str = str.replaceAll("ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ", "u");
//        str = str.replaceAll("ỳ|ý|ỵ|ỷ|ỹ", "y");
//        str = str.replaceAll("đ", "d");
//
//        str = str.replaceAll("À|Á|Ạ|Ả|Ã|Â|Ầ|Ấ|Ậ|Ẩ|Ẫ|Ă|Ằ|Ắ|Ặ|Ẳ|Ẵ", "A");
//        str = str.replaceAll("È|É|Ẹ|Ẻ|Ẽ|Ê|Ề|Ế|Ệ|Ể|Ễ", "E");
//        str = str.replaceAll("Ì|Í|Ị|Ỉ|Ĩ", "I");
//        str = str.replaceAll("Ò|Ó|Ọ|Ỏ|Õ|Ô|Ồ|Ố|Ộ|Ổ|Ỗ|Ơ|Ờ|Ớ|Ợ|Ở|Ỡ", "O");
//        str = str.replaceAll("Ù|Ú|Ụ|Ủ|Ũ|Ư|Ừ|Ứ|Ự|Ử|Ữ", "U");
//        str = str.replaceAll("Ỳ|Ý|Ỵ|Ỷ|Ỹ", "Y");
//        str = str.replaceAll("Đ", "D");

        return str;
    }

    @GetMapping(path = "/second")
    public String populateData2() {
        AdminAccount account = this.adminAccountService.findByUsernameWithAuthoritiesLoadedEagerly("deptraikhoaito").get();

        Authority admin = this.authorityService.findByRoleWithAdminAccountsLoadedEagerly("ROLE_ADMIN").get();

        account.getAuthorities().add(admin);

        admin.getAdminAccounts().add(account);

        this.authorityService.saveOrUpdate(admin);

        this.adminAccountService.saveOrUpdate(account);

        AdminAccount account2 = this.adminAccountService.findByUsernameWithAuthoritiesLoadedEagerly("deptraithongminh").get();

        Authority admin2 = this.authorityService.findByRoleWithAdminAccountsLoadedEagerly("ROLE_ADMIN").get();

        account2.getAuthorities().add(admin2);

        admin2.getAdminAccounts().add(account2);

        this.authorityService.saveOrUpdate(admin2);

        this.adminAccountService.saveOrUpdate(account2);

        return "100% DONE";
    }

    @GetMapping(path = "/index")
    public String index() {
        this.adminAccountService.createFullTextIndexByColumn("username");
        this.translatorGroupService.createFullTextIndexByColumn("name");
        this.authorService.createFullTextIndexByColumn("name");
        this.categoryService.createFullTextIndexByColumn("name");
        this.alternativeNameService.createFullTextIndexByColumn("name");
        return "INDEX";
    }

    @GetMapping(path = "/third")
    public String populateAuthors() {

        Author author1 = new Author();
        author1.setDob("2000-01-01");
        author1.setName("Eichiro Oda");
        author1.setFakeId("eichiro-oda");
        author1.setAvatarLink("https://comicworld.s3.ap-southeast-1.amazonaws.com/static/download.jpg");

        Author author2 = new Author();
        author2.setDob("2000-01-02");
        author2.setName("Kawa Inozaki");
        author2.setFakeId("kawa-inozaki");
        author2.setAvatarLink("https://comicworld.s3.ap-southeast-1.amazonaws.com/static/author2.jpg");

        Author author3 = new Author();
        author3.setDob("2000-01-03");
        author3.setName("Mino Hitano");
        author3.setFakeId("mino-hitano");
        author3.setAvatarLink("https://comicworld.s3.ap-southeast-1.amazonaws.com/static/author3.jpg");

        Author author8 = new Author();
        author8.setDob("2000-01-04");
        author8.setName("Miyanzoro Kaori");
        author8.setFakeId("miyanzoro-kaori");
        author8.setAvatarLink("https://comicworld.s3.ap-southeast-1.amazonaws.com/static/author4.jpg");

        Author author4 = new Author();
        author4.setDob("2000-01-05");
        author4.setName("Arima Kousei");
        author4.setFakeId("arima-kousei");
        author4.setAvatarLink("https://comicworld.s3.ap-southeast-1.amazonaws.com/static/author5.jpg");

        Author author5 = new Author();
        author5.setDob("2000-01-06");
        author5.setName("Hitana Kamizu");
        author5.setFakeId("hitana-kamizu");
        author5.setAvatarLink("https://comicworld.s3.ap-southeast-1.amazonaws.com/static/author6.jpg");

        Author author6 = new Author();
        author6.setDob("2000-01-07");
        author6.setName("Katobi Itto");
        author6.setFakeId("katobi-itto");
        author6.setAvatarLink("https://comicworld.s3.ap-southeast-1.amazonaws.com/static/author6.jpg");

        Author author7 = new Author();
        author7.setDob("2000-01-08");
        author7.setName("Yura Kazuma");
        author7.setFakeId("yura-kazuma");
        author7.setAvatarLink("https://comicworld.s3.ap-southeast-1.amazonaws.com/static/author7.jpg");

        this.authorService.saveOrUpdateAll(Arrays.asList(
            author1,
            author2,
            author3,
            author4,
            author5,
            author6,
            author7,
            author8
        ));

        return "authors";
    }

    @GetMapping(path = "/fourth")
    public String populateCategories() {

        Category category1 = new Category();
        category1.setName("action");
        category1.setFakeId("action");

        Category category2 = new Category();
        category2.setName("romance");
        category2.setFakeId("romance");

        Category category3 = new Category();
        category3.setName("mystery");
        category3.setFakeId("mystery");

        Category category4 = new Category();
        category4.setName("adventure");
        category4.setFakeId("adventure");

        Category category5 = new Category();
        category5.setName("horror");
        category5.setFakeId("horror");

        Category category10 = new Category();
        category10.setName("school life");
        category10.setFakeId("school-life");

        Category category6 = new Category();
        category6.setName("adult");
        category6.setFakeId("adult");

        Category category7 = new Category();
        category7.setName("comedy");
        category7.setFakeId("comedy");

        Category category8 = new Category();
        category8.setName("manhwa");
        category8.setFakeId("manhwa");

        Category category9 = new Category();
        category9.setName("fantasy");
        category9.setFakeId("fantasy");

        this.categoryService.saveOrUpdateAll(Arrays.asList(
                category1,
                category5,
                category2,
                category3,
                category4,
                category6,
                category7,
                category8,
                category9,
                category10
        ));

        return "categories";
    }

    @GetMapping(path = "/fifth")
    public String populateSource() {
        Source source = new Source();
        source.setName("AWS_S3");
        source.setStorageType("S3");

        this.sourceService.saveOrUpdate(source);

        return "source";
    }


}



















