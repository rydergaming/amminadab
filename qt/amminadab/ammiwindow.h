#ifndef AMMIWINDOW_H
#define AMMIWINDOW_H

#include <QMainWindow>

namespace Ui {
class AmmiWindow;
}

class AmmiWindow : public QWidget
{
    Q_OBJECT

public:
    explicit AmmiWindow(QWidget *parent = 0);
    ~AmmiWindow();

private:
    Ui::AmmiWindow *ui;
};

#endif // AMMIWINDOW_H
