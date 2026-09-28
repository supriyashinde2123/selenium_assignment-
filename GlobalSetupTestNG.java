package setup;

import config.TestConfig;

public class GlobalSetupTestNG {
    public void globalSetup(){
     GlobalAuthSetup.loginAndSaveState(
                TestConfig.USERNAME,
                TestConfig.PASSWORD,
                TestConfig.USER_STATE);
       // GlobalAuthSetup.createExpiredState(TestConfig.EXPIRED_STATE);


    }
}
