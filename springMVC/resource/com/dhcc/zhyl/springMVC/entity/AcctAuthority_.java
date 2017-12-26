package com.dhcc.zhyl.springMVC.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-11-04T15:00:45.739+0800")
@StaticMetamodel(AcctAuthority.class)
public class AcctAuthority_ {
	public static volatile SingularAttribute<AcctAuthority, String> id;
	public static volatile SingularAttribute<AcctAuthority, String> name;
	public static volatile SetAttribute<AcctAuthority, AcctRole> acctRoles;
}
