package iu.e510.message.board.tom;

import iu.e510.message.board.tom.common.LamportClock;
import iu.e510.message.board.tom.common.MessageType;
import iu.e510.message.board.tom.common.Payload;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class TestUnicastMessaging {

    private static MessageService messageService;
    private static String unicastServerBindURL = "tcp://localhost:8088";
    private static LamportClock clock;

    @BeforeSuite
    public static void setup() {
        clock = LamportClock.getClock();
        messageService = new MessageServiceImpl(unicastServerBindURL, unicastServerBindURL, null);
    }

    @Test
    public void testInit() {
        Assert.assertEquals(0, clock.get());
    }

    @Test(dependsOnMethods = {"testInit"})
    public void testMessageSent() {
        clock.set(0);
        messageService.send_unordered(new Payload<>("hello world"), unicastServerBindURL,
                MessageType.SYNC);
        int newClock = clock.get();
        Assert.assertEquals(newClock, 2);
    }

    @AfterSuite
    public static void shutdown() {
        messageService.stop_service();
    }
}
