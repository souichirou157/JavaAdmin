
 const sql = require('mysql2');//ドライバ
 const env = require('dotenv').config();
 const express = require('express');
 const {Sequelize ,DataTypes, QueryTypes}= require('sequelize');
 //スキーマをマッピングしてる
 const SequelizeConfig = new Sequelize('STUDENTADMIN_INFO','root','',{host:'localhost',dialect:'mysql'});
 const app = express();


class DB_CONIFG{
  private sql:any = require('mysql2');//ドライバ
  private config:any = sql.createConnection({ host:'localhost', user: 'root',password:'',database:'STUDENTADMIN_INFO'});
  private rowCount:number =0;
  constructor(){
    this.config.connect(()=>{ console.log('opendashbord');});
  }
  
  public getRowCount() {
	
    this.config.query('SELECT COUNT(*)  AS VAL FROM STUDENT_PRIVATE ',(err:any,results:any) => {
		
		console.log(Number(results[0]['VAL']))	
		this.rowCount+=Number(results[0]['VAL']);
	});
	return this.rowCount;
  }
   public showTable() {
	
    this.config.query('show tables',(err:any,results:any) => {
		
		console.log(results);		
	});

  }
  
  public getRow(){
	  
	  return this.rowCount;
  }
}

const conect:DB_CONIFG =  new DB_CONIFG();

let rows:number = conect.getRow()/10;
 conect.showTable();

module.exports ={
RowCount:conect.getRowCount(),
rows:rows
}





// const SequelizeConfig = new Sequelize(process.env.DATABASE_NAME,process.env.DATABASE_USER,process.env.DATABASE_PASSWORD,
//     {host:process.env.DATABASE_HOST,dialect:process.env.DATABASE_ENGINE}); 
