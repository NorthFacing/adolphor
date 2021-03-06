package y2020.m01.d23.publish.subscribe.v2;

/**
 * @author adolphor
 */
public class PublishSubscribeDemo {
  public static void main(String[] args) {
    // 初始化主题
    ConcreteSubject subject = new ConcreteSubject();
    // 实例化一个监听者
    Observer observer = new ConcreteObserver();
    // 将监听者添加到通知列表
    subject.addObserver(observer);
    // 当主题发生变化或者需要通知监听者的时候进行全量通知
    subject.changeState("I'm changed, will notify all observers");
    subject.notifyObservers("I'm changed, will notify all observers");
  }
}
