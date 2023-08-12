public class TestClass {

    @Timed(500)
    public void method1() throws InterruptedException {
        Thread.sleep(202);
    }

    @Timed(200)
    public void method2() throws InterruptedException {
        Thread.sleep(202);
    }
}
