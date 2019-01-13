package org.sec.core.enumeration;

/**
 * @author zhouwei
 * @since 2016/3/24
 */
public enum Workday {

    MONDAY {
        public void work() {
            System.out.println("MONDAY");
        }
        public void firstDayOfWeek() {
            System.out.println("firstDayOfWeek");
        }
    },
    TUESDAY {
        public void work() {
            System.out.println("TUESDAY");
        }
    },
    WEDNESDAY {
        public void work() {
            System.out.println("WEDNESDAY");
        }
    },
    THURSDAY {
        public void work() {
            System.out.println("THURSDAY");
        }
    },
    FRIDAY {
        public void work() {
            System.out.println("FRIDAY");
        }
    };

    abstract public void work();

    public static void main(String[] args) {
        Workday.MONDAY.work();
        // compile error
        // Workday.MONDAY.firstDayOfWeek();
    }
}
