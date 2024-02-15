create table veiculo (
	id bigint not null auto_increment,
    proprietario_id bigint,
    marca varchar(20) not null,
    modelo varchar (20) not null,
    placa varchar(7) not null,
    status varchar (20) not null,
    data_cadastro date not null,
    data_apreensao datetime,
    primary key (id),
    constraint fk_veiculo_proprietario foreign key (proprietario_id) references proprietario (id),
    constraint uk_veiculo unique(placa)
);