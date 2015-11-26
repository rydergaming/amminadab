#include "ammiwindow.h"
#include "ui_ammiwindow.h"

AmmiWindow::AmmiWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::AmmiWindow)
{
    ui->setupUi(this);
}

AmmiWindow::~AmmiWindow()
{
    delete ui;
}
