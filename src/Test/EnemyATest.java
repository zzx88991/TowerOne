package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Model.Enemies;
import Model.EnemyA;

public class EnemyATest {

	@Test
	public void test() {
		Enemies A  =  new EnemyA();
		System.out.println(A.getLocation().x);
		System.out.println(A.getLocation().y);
		A.changeLocation();
		System.out.println(A.getLocation().x);
		System.out.println(A.getLocation().y);
	}

}
