package com.dhcc.zhyl.springMVC.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-11-04T15:00:46.948+0800")
@StaticMetamodel(AcctRole.class)
public class AcctRole_ {
	public static volatile SingularAttribute<AcctRole, String> id;
	public static volatile SingularAttribute<AcctRole, String> name;
	public static volatile SetAttribute<AcctRole, AcctUser> acctUsers;
	public static volatile SetAttribute<AcctRole, AcctAuthority> acctAuthorities;
}
