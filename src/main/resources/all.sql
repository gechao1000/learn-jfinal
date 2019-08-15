#sql("demo")
  insert into demo (`id`,`name`)
  values
  #for(x : data)
    (#para(x.id),#para(x.name)) #(for.last ? "" : ",")
  #end
  on duplicate key
  update `id` = values(`id`), `name` = values(`name`)
#end
