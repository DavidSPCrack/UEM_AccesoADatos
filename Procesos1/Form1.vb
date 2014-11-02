Public Class Form1

    Private Sub Button1_Click(sender As Object, e As EventArgs) Handles Button1.Click

        Dim procesoCalculadora As New Process
        procesoCalculadora.StartInfo.FileName = "CALC.EXE"
        procesoCalculadora.StartInfo.Arguments = ""
        procesoCalculadora.Start()

    End Sub

    Private Sub Button2_Click(sender As Object, e As EventArgs) Handles Button2.Click
        Dim procesoWord As New Process
        procesoWord.StartInfo.FileName = "WINWORD.EXE"
        procesoWord.StartInfo.Arguments = ""
        procesoWord.Start()
    End Sub
End Class
