"use strict";
const sql = require('mysql2'); //ドライバ
const env = require('dotenv').config();
const express = require('express');
const { Sequelize, DataTypes, QueryTypes } = require('sequelize');
//スキーマをマッピングしてる
const SequelizeConfig = new Sequelize('STUDENTADMIN_INFO', 'root', '', { host: 'localhost', dialect: 'mysql' });
const app = express();
class DB_CONIFG {
    constructor() {
        this.sql = require('mysql2'); //ドライバ
        this.config = sql.createConnection({ host: 'localhost', user: 'root', password: '', database: 'STUDENTADMIN_INFO' });
        this.rowCount = 0;
        this.config.connect(() => { console.log('opendashbord'); });
    }
    getRowCount() {
        this.config.query('SELECT COUNT(*)  AS VAL FROM STUDENT_PRIVATE ', (err, results) => {
            console.log(Number(results[0]['VAL']));
            this.rowCount += Number(results[0]['VAL']);
        });
        return this.rowCount;
    }
    showTable() {
        this.config.query('show tables', (err, results) => {
            console.log(results);
        });
    }
    getRow() {
        return this.rowCount;
    }
}
const conect = new DB_CONIFG();
let rows = conect.getRow() / 10;
conect.showTable();
module.exports = {
    RowCount: conect.getRowCount(),
    rows: rows
};
// const SequelizeConfig = new Sequelize(process.env.DATABASE_NAME,process.env.DATABASE_USER,process.env.DATABASE_PASSWORD,
//     {host:process.env.DATABASE_HOST,dialect:process.env.DATABASE_ENGINE}); 
