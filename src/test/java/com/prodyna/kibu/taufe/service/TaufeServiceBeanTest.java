package com.prodyna.kibu.taufe.service;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.prodyna.kibu.taufe.Taufe;
import com.prodyna.kibu.taufe.TaufeHistory;
import com.prodyna.kibu.taufe.TaufeHistoryService;
import com.prodyna.kibu.taufe.TaufeService;

@RunWith(Arquillian.class)
public class TaufeServiceBeanTest {

	@Deployment
	public static JavaArchive createDeployment() {
		JavaArchive jar = ShrinkWrap
				.create(JavaArchive.class)
				.addPackages(true, "com.prodyna.kibu")
				.addAsManifestResource(
						new File("src/main/webapp/WEB-INF/beans.xml"))
				.addAsManifestResource(
						new File("src/main/webapp/WEB-INF/persistence.xml"));
		return jar;
	}

	@Inject
	private TaufeService ts;

	@Inject
	private TaufeHistoryService ths;

	@Test
	@InSequence(1)
	public void testTaufe() {
		Assert.assertNotNull("Inject hat nicht geklappt", ts);
		int before = ts.list().size();
		Taufe t = new Taufe("Darko");
		t = ts.add(t);
		Assert.assertNotNull(t.getId());
		List<Taufe> all = ts.list();
		int after = all.size();
		Taufe last = all.get(all.size() - 1);
		Assert.assertEquals(before + 1, after);
		Assert.assertEquals(t, last);
	}

	@Test
	public void testTaufeHistory() {
		Taufe t = new Taufe("Oliver");
		t = ts.add(t);
		t.setName("Olivier");
		ts.update(t);

		List<TaufeHistory> thss = ths.list(t);
		Assert.assertEquals(2, thss.size());
		Assert.assertEquals("Oliver", thss.get(0).getName());
		Assert.assertEquals("Olivier", thss.get(1).getName());
	}

}
