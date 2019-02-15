use db_aa;

select * from user;

select * from mysql.user;
-- 若计划任务存在则删除

DROP EVENT IF EXISTS drop_user_expire_0;
-- 创建计划任务
CREATE EVENT drop_user_expire_0
  ON SCHEDULE AT CURRENT_TIMESTAMP + INTERVAL 1 minute
  ON COMPLETION not PRESERVE
DO
 call producer_drop_user_expire_0();

/*创建存储过程*/
DELIMITER $$
  drop procedure if exists producer_drop_user_expire_0;
  create procedure producer_drop_user_expire_0()
  begin
    insert into user(name,create_time,expire_time,enable)
    value ('h',now(),subtime(now(),-900),0);
  end$$
DELIMITER ;


/* STARTS TIMESTAMP '2019-01-01 00:00:00'*/