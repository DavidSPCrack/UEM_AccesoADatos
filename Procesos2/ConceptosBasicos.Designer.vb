<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class ConceptosBasicos
    Inherits System.Windows.Forms.Form

    'Form reemplaza a Dispose para limpiar la lista de componentes.
    <System.Diagnostics.DebuggerNonUserCode()> _
    Protected Overrides Sub Dispose(ByVal disposing As Boolean)
        Try
            If disposing AndAlso components IsNot Nothing Then
                components.Dispose()
            End If
        Finally
            MyBase.Dispose(disposing)
        End Try
    End Sub

    'Requerido por el Diseñador de Windows Forms
    Private components As System.ComponentModel.IContainer

    'NOTA: el Diseñador de Windows Forms necesita el siguiente procedimiento
    'Se puede modificar usando el Diseñador de Windows Forms.  
    'No lo modifique con el editor de código.
    <System.Diagnostics.DebuggerStepThrough()> _
    Private Sub InitializeComponent()
        Me.Start1 = New System.Windows.Forms.Button()
        Me.Stop1 = New System.Windows.Forms.Button()
        Me.Stop2 = New System.Windows.Forms.Button()
        Me.Start2 = New System.Windows.Forms.Button()
        Me.Stop3 = New System.Windows.Forms.Button()
        Me.Start3 = New System.Windows.Forms.Button()
        Me.Start4 = New System.Windows.Forms.Button()
        Me.Label1 = New System.Windows.Forms.Label()
        Me.ProgressBar1 = New System.Windows.Forms.ProgressBar()
        Me.TextBox1 = New System.Windows.Forms.TextBox()
        Me.TextBox2 = New System.Windows.Forms.TextBox()
        Me.TextBox3 = New System.Windows.Forms.TextBox()
        Me.SuspendLayout()
        '
        'Start1
        '
        Me.Start1.Location = New System.Drawing.Point(10, 16)
        Me.Start1.Name = "Start1"
        Me.Start1.Size = New System.Drawing.Size(95, 27)
        Me.Start1.TabIndex = 0
        Me.Start1.Text = "Iniciar hilo 1"
        Me.Start1.UseVisualStyleBackColor = True
        '
        'Stop1
        '
        Me.Stop1.Enabled = False
        Me.Stop1.Location = New System.Drawing.Point(111, 16)
        Me.Stop1.Name = "Stop1"
        Me.Stop1.Size = New System.Drawing.Size(95, 27)
        Me.Stop1.TabIndex = 1
        Me.Stop1.Text = "Parar hilo 1"
        Me.Stop1.UseVisualStyleBackColor = True
        '
        'Stop2
        '
        Me.Stop2.Enabled = False
        Me.Stop2.Location = New System.Drawing.Point(111, 73)
        Me.Stop2.Name = "Stop2"
        Me.Stop2.Size = New System.Drawing.Size(95, 27)
        Me.Stop2.TabIndex = 3
        Me.Stop2.Text = "Parar hilo 2"
        Me.Stop2.UseVisualStyleBackColor = True
        '
        'Start2
        '
        Me.Start2.Location = New System.Drawing.Point(10, 73)
        Me.Start2.Name = "Start2"
        Me.Start2.Size = New System.Drawing.Size(95, 27)
        Me.Start2.TabIndex = 2
        Me.Start2.Text = "Iniciar hilo 2"
        Me.Start2.UseVisualStyleBackColor = True
        '
        'Stop3
        '
        Me.Stop3.Enabled = False
        Me.Stop3.Location = New System.Drawing.Point(111, 128)
        Me.Stop3.Name = "Stop3"
        Me.Stop3.Size = New System.Drawing.Size(95, 27)
        Me.Stop3.TabIndex = 5
        Me.Stop3.Text = "Parar hilo 3"
        Me.Stop3.UseVisualStyleBackColor = True
        '
        'Start3
        '
        Me.Start3.Location = New System.Drawing.Point(10, 128)
        Me.Start3.Name = "Start3"
        Me.Start3.Size = New System.Drawing.Size(95, 27)
        Me.Start3.TabIndex = 4
        Me.Start3.Text = "Iniciar hilo 3"
        Me.Start3.UseVisualStyleBackColor = True
        '
        'Start4
        '
        Me.Start4.Location = New System.Drawing.Point(10, 188)
        Me.Start4.Name = "Start4"
        Me.Start4.Size = New System.Drawing.Size(95, 27)
        Me.Start4.TabIndex = 6
        Me.Start4.Text = "Iniciar hilo 4"
        Me.Start4.UseVisualStyleBackColor = True
        '
        'Label1
        '
        Me.Label1.AutoSize = True
        Me.Label1.Location = New System.Drawing.Point(266, 23)
        Me.Label1.Name = "Label1"
        Me.Label1.Size = New System.Drawing.Size(13, 13)
        Me.Label1.TabIndex = 7
        Me.Label1.Text = "0"
        '
        'ProgressBar1
        '
        Me.ProgressBar1.Location = New System.Drawing.Point(212, 73)
        Me.ProgressBar1.Name = "ProgressBar1"
        Me.ProgressBar1.Size = New System.Drawing.Size(297, 26)
        Me.ProgressBar1.TabIndex = 8
        '
        'TextBox1
        '
        Me.TextBox1.Location = New System.Drawing.Point(212, 128)
        Me.TextBox1.Multiline = True
        Me.TextBox1.Name = "TextBox1"
        Me.TextBox1.ReadOnly = True
        Me.TextBox1.Size = New System.Drawing.Size(91, 27)
        Me.TextBox1.TabIndex = 9
        '
        'TextBox2
        '
        Me.TextBox2.Location = New System.Drawing.Point(309, 128)
        Me.TextBox2.Multiline = True
        Me.TextBox2.Name = "TextBox2"
        Me.TextBox2.ReadOnly = True
        Me.TextBox2.Size = New System.Drawing.Size(91, 27)
        Me.TextBox2.TabIndex = 10
        '
        'TextBox3
        '
        Me.TextBox3.Location = New System.Drawing.Point(406, 128)
        Me.TextBox3.Multiline = True
        Me.TextBox3.Name = "TextBox3"
        Me.TextBox3.ReadOnly = True
        Me.TextBox3.Size = New System.Drawing.Size(91, 27)
        Me.TextBox3.TabIndex = 11
        '
        'Form1
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 13.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(521, 240)
        Me.Controls.Add(Me.TextBox3)
        Me.Controls.Add(Me.TextBox2)
        Me.Controls.Add(Me.TextBox1)
        Me.Controls.Add(Me.ProgressBar1)
        Me.Controls.Add(Me.Label1)
        Me.Controls.Add(Me.Start4)
        Me.Controls.Add(Me.Stop3)
        Me.Controls.Add(Me.Start3)
        Me.Controls.Add(Me.Stop2)
        Me.Controls.Add(Me.Start2)
        Me.Controls.Add(Me.Stop1)
        Me.Controls.Add(Me.Start1)
        Me.Name = "Form1"
        Me.Text = "Conceptos básicos"
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub
    Friend WithEvents Start1 As System.Windows.Forms.Button
    Friend WithEvents Stop1 As System.Windows.Forms.Button
    Friend WithEvents Stop2 As System.Windows.Forms.Button
    Friend WithEvents Start2 As System.Windows.Forms.Button
    Friend WithEvents Stop3 As System.Windows.Forms.Button
    Friend WithEvents Start3 As System.Windows.Forms.Button
    Friend WithEvents Start4 As System.Windows.Forms.Button
    Friend WithEvents Label1 As System.Windows.Forms.Label
    Friend WithEvents ProgressBar1 As System.Windows.Forms.ProgressBar
    Friend WithEvents TextBox1 As System.Windows.Forms.TextBox
    Friend WithEvents TextBox2 As System.Windows.Forms.TextBox
    Friend WithEvents TextBox3 As System.Windows.Forms.TextBox

End Class
