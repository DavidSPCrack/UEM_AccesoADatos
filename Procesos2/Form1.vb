Imports System.Threading

Public Class Form1

#Region "Eventos de Hilo y sus Handlers"

    Private Event InicioHilo(ByVal Inicio As DateTime, ByVal Hilo As Integer)
    Private Event FinHilo(ByVal Fin As DateTime, ByVal Hilo As Integer)

    Private HilosWorking As Boolean() = {False, False, False, False}

    Private Sub Form3_Load(sender As Object, e As EventArgs) Handles MyBase.Load
        AddHandler InicioHilo, AddressOf InicioHiloSub
        AddHandler FinHilo, AddressOf FinHiloSub

        CheckForIllegalCrossThreadCalls = False

    End Sub

    Private Sub InicioHiloSub(ByVal Inicio As DateTime, ByVal Hilo As Integer)
        HilosWorking(Hilo) = True
    End Sub

    Private Sub FinHiloSub(ByVal Inicio As DateTime, ByVal Hilo As Integer)
        HilosWorking(Hilo) = False
    End Sub

    Private Sub Form1_Load(sender As Object, e As EventArgs) Handles MyBase.Load

    End Sub

#End Region

#Region "Hilo 1"

    Private Sub PararHilo1()
        Stop1.Enabled = False
        RaiseEvent FinHilo(DateTime.Now, 0)
        Start1.Enabled = True
    End Sub

    Private Sub InicializarHilo1()
        Start1.Enabled = False
        Dim hilo As New Thread(AddressOf Hilo1Method)
        hilo.Name = "Hilo1"
        hilo.IsBackground = True
        hilo.Start()
        Stop1.Enabled = True
    End Sub

    Private Sub Hilo1Method()
        RaiseEvent InicioHilo(DateTime.Now, 0)
        While HilosWorking(0)
            Dim num As Integer = Label1.Text
            num += 1
            Label1.Text = num
            Thread.Sleep(1000)
        End While
    End Sub

    Private Sub Start1_Click(sender As Object, e As EventArgs) Handles Start1.Click
        If Not HilosWorking(0) Then
            InicializarHilo1()
        End If
    End Sub

    Private Sub Stop1_Click(sender As Object, e As EventArgs) Handles Stop1.Click
        If HilosWorking(0) Then
            PararHilo1()
        End If
    End Sub

#End Region
#Region "Hilo 2"
    Private Sub PararHilo2()
        Stop2.Enabled = False
        RaiseEvent FinHilo(DateTime.Now, 1)
        Start2.Enabled = True
    End Sub

    Private Sub InicializarHilo2()
        Start2.Enabled = False
        Dim hilo As New Thread(AddressOf Hilo2Method)
        hilo.Name = "Hilo2"
        hilo.IsBackground = True
        hilo.Start()
        Stop2.Enabled = True
    End Sub

    Private Sub Hilo2Method()
        RaiseEvent InicioHilo(DateTime.Now, 1)
        While HilosWorking(1)
            Dim num As Integer = ProgressBar1.Value
            If num > 99 Then
                num = 0
            End If
            num += 1
            ProgressBar1.Value = num
            If num = 100 Then
                Thread.Sleep(1000)
            End If
            Thread.Sleep(50)
        End While
    End Sub
    Private Sub Start2_Click(sender As Object, e As EventArgs) Handles Start2.Click
        If Not HilosWorking(1) Then
            InicializarHilo2()
        End If
    End Sub

    Private Sub Stop2_Click(sender As Object, e As EventArgs) Handles Stop2.Click
        If HilosWorking(1) Then
            PararHilo2()
        End If
    End Sub

#End Region
#Region "Hilo 3"
    Private Sub PararHilo3()
        Stop3.Enabled = False
        RaiseEvent FinHilo(DateTime.Now, 2)
        Start3.Enabled = True
    End Sub

    Private Sub InicializarHilo3()
        Start3.Enabled = False
        Dim hilo As New Thread(AddressOf Hilo3Method)
        hilo.Name = "Hilo3"
        hilo.IsBackground = True
        hilo.Start()
        Stop3.Enabled = True
    End Sub

    Private Sub Hilo3Method()
        RaiseEvent InicioHilo(DateTime.Now, 2)
        While HilosWorking(2)
            TextBox1.BackColor = GetRandomColor(1)
            TextBox2.BackColor = GetRandomColor(2)
            TextBox3.BackColor = GetRandomColor(3)
            Thread.Sleep(500)
        End While
    End Sub

    Private Function GetRandomColor(ByVal num As Integer)
        Dim rnd1 As New Random(CInt(Date.Now.Ticks Mod Integer.MaxValue) + num * 1337)
        Dim mColor As Color

        mColor = Color.FromArgb(rnd1.Next(0, 256), rnd1.Next(0, 256), rnd1.Next(0, 256))
        Return mColor
    End Function
    Private Sub Start3_Click(sender As Object, e As EventArgs) Handles Start3.Click
        If Not HilosWorking(2) Then
            InicializarHilo3()
        End If
    End Sub

    Private Sub Stop3_Click(sender As Object, e As EventArgs) Handles Stop3.Click
        If HilosWorking(2) Then
            PararHilo3()
        End If
    End Sub

#End Region
#Region "Hilo 4"

    Private Sub PararHilo4()
        RaiseEvent FinHilo(DateTime.Now, 3)
        Start4.Enabled = True
    End Sub
    Private Sub InicializarHilo4()
        Start4.Enabled = False
        Dim hilo As New Thread(AddressOf Hilo4Method)
        hilo.Name = "Hilo4"
        hilo.IsBackground = True
        hilo.Start()
    End Sub

    Private Sub Hilo4Method()
        RaiseEvent InicioHilo(DateTime.Now, 3)
        While HilosWorking(3)
            MsgBox("Ejecución del hilo 4")
            PararHilo4()
        End While
    End Sub
    Private Sub Start4_Click(sender As Object, e As EventArgs) Handles Start4.Click
        If Not HilosWorking(3) Then
            InicializarHilo4()
        End If
    End Sub
#End Region
End Class
