import java.util.ArrayList;
import java.util.List;

public class ObserverExample {

    // Subject class
    static class Subject {
        private List<Observer> observers = new ArrayList<>();
        private int state;

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
            notifyAllObservers();
        }

        public void attach(Observer observer) {
            observers.add(observer);
        }

        public void notifyAllObservers() {
            for (Observer observer : observers) {
                observer.update();
            }
        }
    }

    // Observer abstract class
    abstract static class Observer {
        protected Subject subject;
        public abstract void update();
    }

    // Concrete Observer class that converts state to Hexadecimal
    static class HexObserver extends Observer {
        public HexObserver(Subject subject) {
            this.subject = subject;
            this.subject.attach(this);
        }

        public void update() {
            System.out.println("Hex String: " + Integer.toHexString(subject.getState()).toUpperCase());
        }
    }

    // Concrete Observer class that converts state to Octal
    static class OctalObserver extends Observer {
        public OctalObserver(Subject subject) {
            this.subject = subject;
            this.subject.attach(this);
        }

        public void update() {
            System.out.println("Octal String: " + Integer.toOctalString(subject.getState()));
        }
    }

    // Main method
    public static void main(String[] args) {
        Subject subject = new Subject();

        new HexObserver(subject);
        new OctalObserver(subject);

        System.out.println("First state change: 15");
        subject.setState(15);
        System.out.println("Second state change: 10");
        subject.setState(10);
    }
}

