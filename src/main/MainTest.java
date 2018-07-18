package main;

import java.io.Reader;
import java.text.MessageFormat;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.dao.IUser;
import com.models.User;

public class MainTest {
	private static SqlSessionFactory sqlSessionFactory;
	private static Reader reader;
	private static IUser iUser;
	private static SqlSession session;
	
	static {
		try {
			reader = Resources.getResourceAsReader("config/Configure.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
//			sqlSessionFactory.getConfiguration().addMapper(IUser.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		session = sqlSessionFactory.openSession();
		iUser = session.getMapper(IUser.class);
		try {
//			getUserList();
			
//			getUser();
			
//			testDelete();
			
			testUpdate();
			
		} finally {
			// TODO: handle finally clause
			session.close();
		}
	}
	
	public static void getUser() {
		User user = iUser.getUserByID(1);
//			User user = (User) session.selectOne("com.models.UserMapper.GetUserByID",1);
		if (user!=null) {
			System.out.println("名字："+user.getName()+", 所属部门："+user.getDept()+", 主页："+user.getWebsite());
		}
	}

	
	public static void testUpdate()
    {
        try
        {
            System.out.println("Test update start...");
            printUsers(iUser.getUserList());
            // 执行更新
            User user = iUser.getUserByID(1);
            user.setName("New name");
            iUser.updateUser(user);
            // 提交事务
            session.commit();
            // 显示更新之后User信息
            System.out.println("After update");
            printUsers(iUser.getUserList());
            System.out.println("Test update finished...");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // 删除用户信息
    public static void testDelete()
    {
        try
        {
            System.out.println("Test delete start...");
            // 显示删除之前User信息
            System.out.println("Before delete");
            printUsers(iUser.getUserList());
            // 执行删除
            iUser.deleteUser(2);
            // 提交事务
            session.commit();
            // 显示删除之后User信息
            System.out.println("After delete");
            printUsers(iUser.getUserList());
            System.out.println("Test delete finished...");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public static void getUserList() {
        try {
            // 显示User信息
            System.out.println("Test Get start...");
            printUsers(iUser.getUserList());
            System.out.println("Test Get finished...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	
	private static void printUsers(List<User> users) {
		int count = 0;
		
		for (User user : users) {
            System.out.println(MessageFormat.format(
                    "============= User[{0}]=================", ++count));
            System.out.println("User Id: " + user.getId());
            System.out.println("User Name: " + user.getName());
            System.out.println("User Dept: " + user.getDept());
            System.out.println("User Website: " + user.getWebsite());
			
		}
	}

}
