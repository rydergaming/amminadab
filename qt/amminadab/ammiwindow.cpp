#include "ammiwindow.h"
#include "ui_ammiwindow.h"
#include "samu.hpp"
#include <iostream>
#include <string>
#include <sstream>
#include <signal.h>


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
void AmmiWindow::setLabelText(const QString &text)
{
    qDebug() << text;
    ui->label->setText(text);
}


