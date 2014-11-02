Imports System.Threading

Public Class Form2

    Dim thread1 As Thread
    Dim thread2 As Thread


    Private Sub Button1_Click(sender As Object, e As EventArgs) Handles Button1.Click
        If (Not thread1.IsAlive) Then
            thread1.Start()
        End If

    End Sub

    Private Sub Button2_Click(sender As Object, e As EventArgs) Handles Button2.Click
        If (Not thread2.IsAlive) Then
            thread2.Start()
        End If
    End Sub

    Private Sub Mensaje()
        MsgBox("Hola buenos días DAM HCAP")

    End Sub

    Private Sub establecerColores()
        TextBox1.BackColor = Color.Blue
        TextBox2.BackColor = Color.Red
        TextBox3.BackColor = Color.Green
    End Sub

    Private Sub TextBox2_TextChanged(sender As Object, e As EventArgs) Handles TextBox2.TextChanged

    End Sub

    Private Sub Form2_Load(sender As Object, e As EventArgs) Handles Me.Load
        thread1 = New Thread(AddressOf Mensaje)
        thread2 = New Thread(AddressOf establecerColores)
    End Sub
End Class