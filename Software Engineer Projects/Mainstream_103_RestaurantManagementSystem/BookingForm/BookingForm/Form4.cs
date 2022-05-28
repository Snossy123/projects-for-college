using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using CrystalDecisions.Shared;
namespace BookingForm
{
    public partial class Form4 : Form
    {
        CrystalReport1 cr;
        public Form4()
        {
            InitializeComponent();
        }
       
        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void crystalReportViewer1_Load(object sender, EventArgs e)
        {

        }

        private void Form4_Load_1(object sender, EventArgs e)
        {
            cr = new CrystalReport1();
            foreach (ParameterDiscreteValue v in cr.ParameterFields[0].DefaultValues)
                cmb_bookind_id.Items.Add(v.Value);
            
        }

        private void button2_Click_1(object sender, EventArgs e)
        {
            cr.SetParameterValue(0, cmb_bookind_id.Text);
            crystalReportViewer1.ReportSource = cr;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            this.Hide();
            Form2 f2 = new Form2();
            f2.Show();
        }
    }
}
