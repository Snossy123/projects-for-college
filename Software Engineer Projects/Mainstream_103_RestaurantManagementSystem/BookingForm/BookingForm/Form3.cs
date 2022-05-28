using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Oracle.DataAccess.Client;//execte queries and link between c# application and sql database
using Oracle.DataAccess.Types;
namespace BookingForm
{
    public partial class Form3 : Form
    {
        OracleDataAdapter adapter;
        OracleCommandBuilder builder;
        DataSet ds;
        public Form3()
        {
            InitializeComponent();
        }


        private void Form3_Load(object sender, EventArgs e)
        {
            //to execute the select statement we need to hold result from select statement :
            //1-orcle data reader -> container hold results returned from statements, connected fetch one row at a time if need more use loop, read only cant modify
            //2-dataset->in c# application
        }

        private void radioButton1_CheckedChanged(object sender, EventArgs e)
        {

        }

        private void radioButton2_CheckedChanged(object sender, EventArgs e)
        {

        }

        private void button2_Click(object sender, EventArgs e)
        {
            // generate sql statement automatically of what i changed in a certain table->obj of class orclecommand builder need to connect it a certain adapter to know which table will it modify and adapter deal offline
             builder = new OracleCommandBuilder(adapter);//obj
            // builder ready for adapter to give order to generate sql statement to update
            adapter.Update(ds.Tables[0]);// adapter change in database// gives order to builder to see update by user and generate equevelent sql statements and send statements from builder toadapter
            // adapter then update database with stuff sent from builder
            //only adapter communicate withdatabase not builder
        }

        private void button1_Click(object sender, EventArgs e)
        {
            string connectionstring = "Data source=orcl;User Id=hr ;Password=hr;";//connection string to connect to sql developer
            string commandstring = "";
            if (radioButton1.Checked)
            {
                commandstring = "select * from Employees where EMPLOYEE_ID > 4";
            }
            else if (radioButton2.Checked)
            {
                commandstring = "select * from Food where ID <= 4";
            }
            //Create a Data Adapter Object:
            adapter = new OracleDataAdapter(commandstring, connectionstring);// connect with data base and passs select and return what i select and close connection
                                                                             //Create a DataSet Object:
            DataSet ds = new DataSet();//empty
                                       // adapter has data returned from select want to store in dataset put in index zero
            adapter.Fill(ds);// dataset has 1 element
            dataGridView1.DataSource = ds.Tables[0];// for user to interact //connect grid in design with table in data set to fill grid by it

        }

        private void dataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }

        private void button3_Click(object sender, EventArgs e)
        {
            this.Hide();
            Form2 f2 = new Form2();
            f2.Show();
        }
    }
}
