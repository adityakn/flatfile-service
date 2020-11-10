package com.aditya.flatfile.util.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.aditya.flatfile.util.FlatFileUtil;

public class FlatFileUtilTest {
	@Test
	public void testPhone() {
		String s = "(100)-000-0000";
		assertTrue(FlatFileUtil.isPhone(s));

		s = "(100)-000-000";
		assertFalse(FlatFileUtil.isPhone(s));

		s = "100 000 00";
		assertFalse(FlatFileUtil.isPhone(s));

		s = "100 000 0000";
		assertTrue(FlatFileUtil.isPhone(s));

		s = "1000000000";
		assertTrue(FlatFileUtil.isPhone(s));

		s = "100000000";
		assertFalse(FlatFileUtil.isPhone(s));
	}
}