insert into credit (
	user_id,
	amount,
	approved,
	payment_type_id
) values (
	:userId,
	:amount,
	:approved,
	:paymentTypeId
)
