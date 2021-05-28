insert into estado (id, nome) values (nextval('seq_estado_id'), 'Rio Grande do Sul');
insert into cidade (id, nome, estado) values (nextval('seq_cidade_id'), 'Coqueiros do Sul', 1);