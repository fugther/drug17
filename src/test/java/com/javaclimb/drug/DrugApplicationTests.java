package com.javaclimb.drug;

import com.alibaba.druid.filter.config.ConfigTools;
import com.javaclimb.drug.entity.User;
import com.javaclimb.drug.service.IUserService;
import com.javaclimb.drug.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DrugApplicationTests {

    @Autowired
    private IUserService userService;
    @Test
    void contextLoads() {
        User user = new User();
        user.setUsername("admin");
        User user1 = userService.queryUserByUsername(user);
        if(user1!=null){
            System.out.println(user1.getPassword());
        }
    }

    /**
     * druid实现配置中数据库密码加密
     * @throws Exception
     */
    @Test
    public void druidEncrypt() throws Exception {
// 密码明文
        String password = "123456";
        System.out.println("password " + password);
        String [] keyPair = ConfigTools.genKeyPair(512);
        // 私钥
        String privateKey = keyPair[0];
        // 公钥
        String publicKey = keyPair[1];
        // 用私钥加密后的密文
        password = ConfigTools.encrypt(privateKey, password);
        System.out.println("privateKey:"+privateKey);

        System.out.println("publicKey:"+publicKey);
        // 密文
        System.out.println("ciphertext:"+password);
        String decryptPassword = ConfigTools.decrypt(publicKey, password);
        // 原密码
        System.out.println("originalPassword" + decryptPassword);
       }

}
