package cms.view;

import cms.javabean.Customer;
import cms.service.CustomerService;

import java.util.List;

/**
 * 这是主控模块, 负责菜单显示和用户交互. 也称为UI, 内部要频繁到管理器对象, 所以使用对象关联
 */
public class CustomerView {

    /**
     * 关联到的管理器对象
     */
    private CustomerService customerService = new CustomerService();

    /**
     * 进入主菜单, 是项目的真正入口, 不可以轻易结束
     */
    public void enterMainMenu() {
        // 1) 声明布尔
        boolean loopFlag = true;
        // 2) 写循环
        do {
            System.out.println("\n--------------------------------customer management system--------------------------------------\n");
            listAllCustomers();
            System.out.print("1 add customer 2 modify customer 3 delete customer 4 list all customers 5 exit  请选择(1 - 5) : ");
            // 读取用户选择
            char choice = KeyboardUtility.readMenuSelection();
            switch (choice) {
                case '1' : addNewCustomer(); break;
                case '2' : modifyCustomer(); break;
                case '3' : deleteCustomer(); break;
                case '4' : listAllCustomers(); break;
                case '5' :
                    System.out.print("do you want to exit(Y/N) : ");
                    // 获取用户输入的确认
                    char confirm = KeyboardUtility.readConfirmSelection();
                    if (confirm == 'Y') {
                        loopFlag = false;
                    }
                    break;
            }
        } while (loopFlag);
    }

    /**
     * 添加新员工
     */
    private void addNewCustomer() {
        Customer customer = new Customer();
        System.out.println("---------------------add customer---------------------");
        System.out.print("name : ");
        String name = KeyboardUtility.readString(10);
        customer.setName(name);
        System.out.print("gender : ");
        String gender = KeyboardUtility.readString(6);
        customer.setGender(gender);
        System.out.print("age : ");
        int age = KeyboardUtility.readInt();
        customer.setAge(age);
        System.out.print("salary : ");
        int salary = KeyboardUtility.readInt();
        customer.setSalary(salary);
        System.out.print("phone : ");
        String phone = KeyboardUtility.readString(15);
        customer.setPhone(phone);
        // 通过调用管理器对象完成 员工添加
        customerService.addCustomer(customer);
        System.out.println("---------------------add successfully---------------------");
    }

    /**
     * 修改员工
     */
    private void modifyCustomer () {
        System.out.println("---------------------modify customer---------------------");
        System.out.print("please input the ID of the customer you want to modify(-1 exit) : ");
        // 获取用户输入的id
        int id = KeyboardUtility.readInt();
        if (id == -1) {
            return;
        }
        // 根据编号定位要修改的目标对象
        Customer target = customerService.getCustomer(id);
        if (target == null) {
            System.out.println("--------------ID[" + id + "]does not exit-----------------");
            return;
        }
        System.out.println("<not modify by pressing enter>");
        System.out.print("name(" + target.getName() + ") : ");
        String name = KeyboardUtility.readString(10, target.getName());
        target.setName(name);
        System.out.print("age(" + target.getAge() + ") : ");
        int age = KeyboardUtility.readInt(target.getAge());
        target.setAge(age);
        System.out.print("salary(" + target.getSalary() + ") : ");
        int salary = KeyboardUtility.readInt((int) target.getSalary());
        target.setSalary(salary);
        System.out.print("phone(" + target.getPhone() + ") : ");
        String phone = KeyboardUtility.readString(15, target.getPhone());
        target.setPhone(phone);

        customerService.modifyCustomer(id, target);

        System.out.println("---------------------modify successfully---------------------");
    }

    /**
     * 删除员工
     */
    private void deleteCustomer () {
        System.out.println("---------------------delete customer---------------------");
        System.out.print("please input the ID of the customer you want to delete(-1 exit) : ");
        // 获取用户输入的ID
        int id = KeyboardUtility.readInt();
        if (id == -1) {
            return;
        }
        System.out.print("do you want to delete(Y/N) : ");
        // 获取用户输入的确认
        char confirm = KeyboardUtility.readConfirmSelection();
        if (confirm == 'Y') {
            boolean flag = customerService.removeCustomer(id);
            if (flag) {
                System.out.println("---------------------delete successfully---------------------");
            } else {
                System.out.println("--------------ID[" + id + "]customer does not exist-----------------");
            }
        }
    }

    /**
     * 员工列表
     */
    private void listAllCustomers() {
        System.out.println("---------------------------------customer list--------------------------------------");
        // 真的获取所有员工
        List<Customer> list = customerService.getList();
        if (list == null || list.size() == 0) {
            System.out.println("no customer information, please add new customer...");
        } else {
            System.out.println("ID\tname\t\tgender\t\tage\t\tsalary\t\t\tphone");
            for (Customer customer : list) {
                System.out.println(customer);
            }
        }
        System.out.println("-----------------------------------------------------------------------------");
    }

}
