Public Class Form4


    Private Sub Form4_Load(sender As Object, e As EventArgs) Handles MyBase.Load
        ComboBox1.Items.Add("Madrid")
        ComboBox1.Items.Add("Toledo")
        ComboBox1.Items.Add("Cuenca")
        ComboBox1.Items.Add("Segovia")
    End Sub

    Private Sub ComboBox1_SelectedIndexChanged(sender As Object, e As EventArgs) Handles ComboBox1.SelectedIndexChanged
        TextBox1.Text = ComboBox1.SelectedItem
    End Sub

    Private Sub TextBox1_MouseUp(sender As Object, e As MouseEventArgs) Handles TextBox1.MouseUp

        ListBox1.Items.Add("Madrid")
        ListBox1.Items.Add("Toledo")
        ListBox1.Items.Add("Cuenca")
        ListBox1.Items.Add("Segovia")
    End Sub
End Class