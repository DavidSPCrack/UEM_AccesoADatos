Imports System.Threading

Public Class Form3

#Region "Eventos de Hilo y sus Handlers"

    Private Event InicioHilo(ByVal Inicio As DateTime)
    Private Event FinHilo(ByVal Fin As DateTime)

    Private Trabajando As Boolean = False

    Private Sub Form3_Load(sender As Object, e As EventArgs) Handles MyBase.Load
        AddHandler InicioHilo, AddressOf InicioHiloSub
        AddHandler FinHilo, AddressOf FinHiloSub

        CheckForIllegalCrossThreadCalls = False

    End Sub

    Private Sub InicioHiloSub(ByVal Inicio As DateTime)
        Trabajando = True
    End Sub

    Private Sub FinHiloSub(ByVal Inicio As DateTime)
        Trabajando = False
    End Sub

#End Region

#Region "Inicializar el Hilo"

    Private Sub InicializarHilo()
        Dim hilo As New Thread(AddressOf Mensaje)
        hilo.Name = "Hilo_Mensaje"
        hilo.IsBackground = True
        hilo.Start()
        Label2.BackColor = Color.Red
        Label2.ForeColor = Color.White
        Label2.Text = "EN EJECUCIÓN"
    End Sub

    Private Sub Mensaje()
        RaiseEvent InicioHilo(DateTime.Now)
        MsgBox("BUENOS DIAS DAM HCAP")
        Thread.Sleep(10000)

        RaiseEvent FinHilo(DateTime.Now)
        Label2.BackColor = Color.Green
        Label2.ForeColor = Color.White
        Label2.Text = "PARADO"


    End Sub

#End Region

    Private Sub Button1_Click(sender As Object, e As EventArgs) Handles Button1.Click
        If Not Trabajando Then
            InicializarHilo()
        Else
            MsgBox("Estoy en ejecución todavía. Espera unos segundos")
        End If

    End Sub


End Class