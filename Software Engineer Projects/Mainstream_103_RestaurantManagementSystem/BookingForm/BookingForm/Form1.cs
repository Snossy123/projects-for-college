using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Oracle.DataAccess.Client;
using Oracle.DataAccess.Types;
namespace BookingForm
{
    public partial class Form1 : Form
    {
        int idx;
        int idx1;
        string ordb = "Data Source=ORCL;user id=hr;password=hr;";
        OracleConnection conn;
        public Form1()
        {
            InitializeComponent();
        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void label3_Click(object sender, EventArgs e)
        {

        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {

        }

        private void Form1_Load(object sender, EventArgs e)
        {
            conn = new OracleConnection(ordb);
            conn.Open();
            OracleCommand cmd = new OracleCommand();
            cmd.Connection = conn;
            cmd.CommandText = "select id from BOOKING";
            cmd.CommandType = CommandType.Text;
            OracleDataReader dr = cmd.ExecuteReader();
            while (dr.Read())
            {
                comboBox2.Items.Add(dr[0]);
               
            }
            dr.Close();

        }

        private void comboBox2_SelectedIndexChanged(object sender, EventArgs e)
        {
            OracleCommand cmd2 = new OracleCommand();
            cmd2.Connection = conn;
            cmd2.CommandText = "select * from BOOKING where id=:ID";
            cmd2.CommandType = CommandType.Text;
            cmd2.Parameters.Add("ID", comboBox2.SelectedItem.ToString());
            OracleDataReader dr = cmd2.ExecuteReader();
            if (dr.Read())
            {
                
                textBox1.Text = dr[1].ToString();
                textBox2.Text = dr[3].ToString();
                textBox3.Text = dr[4].ToString();
                textBox4.Text=dr[2].ToString();
            }
           
            int r = cmd2.ExecuteNonQuery();
        }

        private void Form1_FormClosing(object sender, FormClosingEventArgs e)
        {
            conn.Dispose();
        }

        private void textBox3_TextChanged(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            OracleCommand cmd2 = new OracleCommand();
            cmd2.Connection = conn;
            cmd2.CommandText = "insert into BOOKING values(:id,:CID ,:nos,:name,:appointment)";
            cmd2.CommandType = CommandType.Text;
            cmd2.Parameters.Add("id", comboBox2.Text);
            cmd2.Parameters.Add("CID", textBox1.Text);
            cmd2.Parameters.Add("nos", textBox4.Text);
            cmd2.Parameters.Add("name", textBox2.Text);
            cmd2.Parameters.Add("appointment", textBox3.Text);
          
            int r = cmd2.ExecuteNonQuery();
            if(r!=-1)
            {
                comboBox2.Items.Add(comboBox2.Text);
                MessageBox.Show("BOOKING SUCSESSFULY");
            }


           
        }

        private void button2_Click(object sender, EventArgs e)
        {
            OracleCommand cmd3 = new OracleCommand();
            cmd3.Connection = conn;
            cmd3.CommandText = "GETCID";
            cmd3.CommandType = CommandType.StoredProcedure;
            cmd3.Parameters.Add("CID",OracleDbType.Int32,ParameterDirection.Output);
             cmd3.Parameters.Add("idx", comboBox2.SelectedItem.ToString());
            cmd3.ExecuteNonQuery();

            idx = Convert.ToInt32(cmd3.Parameters["CID"].Value.ToString());
            textBox5.Text = idx.ToString();

        }

        private void textBox5_TextChanged(object sender, EventArgs e)
        {

        }

        private void button3_Click(object sender, EventArgs e)
        {
            OracleCommand cmd3 = new OracleCommand();
            cmd3.Connection = conn;
            cmd3.CommandText = "GETIDS";
            cmd3.CommandType = CommandType.StoredProcedure;
            cmd3.Parameters.Add("Cur", OracleDbType.RefCursor,ParameterDirection.Output);
            cmd3.Parameters.Add("idx", textBox1.Text);
            OracleDataReader dr= cmd3.ExecuteReader();
            while(dr.Read())
            {
                comboBox1.Items.Add(dr[0]);
            }
            dr.Close();
           
        }

        private void button4_Click(object sender, EventArgs e)
        {
            this.Hide();
            Form2 f2 = new Form2();
            f2.Show();

        }
    }
}
