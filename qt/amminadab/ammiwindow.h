#ifndef AMMIWINDOW_H
#define AMMIWINDOW_H

#include <QMainWindow>
#include <QString>
#include <QDebug>
namespace Ui {
class AmmiWindow;
}

class AmmiWindow : public QMainWindow
{
    Q_OBJECT


public:
    explicit AmmiWindow(QWidget *parent = 0);
    ~AmmiWindow();


private:
    Ui::AmmiWindow *ui;

public Q_SLOTS:
    void setLabelText(const QString &text);

};

#endif // AMMIWINDOW_H
