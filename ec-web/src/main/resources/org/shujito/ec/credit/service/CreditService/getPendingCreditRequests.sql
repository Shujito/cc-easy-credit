select
	c.amount,
	p.months,
	p.interest,
	ifnull(cst.description,'Pendiente') as status,
	amount + (p.interest * p.months * c.amount) as total
from credit c
left join user u
	on c.user_id is u._id
left join payment_type p
	on p._id is c.payment_type_id
left join credit_status cs
	on cs.credit_id is c._id
left join credit_status_type cst
	on cst._id is cs.credit_status_type_id
where
	(u._id is :userId)
and
	(:status is null or status is :status)
order by c._id asc
