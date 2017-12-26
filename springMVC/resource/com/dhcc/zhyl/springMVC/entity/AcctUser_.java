package com.dhcc.zhyl.springMVC.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-11-04T15:00:47.066+0800")
@StaticMetamodel(AcctUser.class)
public class AcctUser_ {
	public static volatile SingularAttribute<AcctUser, String> id;
	public static volatile SingularAttribute<AcctUser, String> nickName;
	public static volatile SingularAttribute<AcctUser, String> telephone;
	public static volatile SingularAttribute<AcctUser, Date> registerTime;
	public static volatile SingularAttribute<AcctUser, String> cardId;
	public static volatile SetAttribute<AcctUser, AcctRole> acctRoles;
}
