#ifndef SAMUMAIN_H
#define SAMUMAIN_H

#include <QObject>
#include <iostream>
#include <string>
#include <sstream>
#include <signal.h>
#include "samu.hpp"
#include "ammiwindow.h"
#include <QThread>

class SamuMain : public QObject
{
    Q_OBJECT
public:
    explicit SamuMain(QObject *parent = 0);
    void run();
   // void doAll();
private:
    double read_cache ( std::string & key, int &cnt, int &brel );
    double to_samu ( int channel, std::string &msg, std::string &key );
    double to_samu ( int channel, std::string &msg );
    double to_samu ( int channel, SPOTriplets &tv );
    Samu samu {"Amminadab", "samu.soul.txt"};
    AmmiWindow *window;
    bool halted {false};
    std::map<std::string, SPOTriplets> cache;

    int samuHasAlreadyLearned {7};
    int reinforcement {0};
    void save_samu ( int sig );

signals:

public slots:
};

#endif // SAMUMAIN_H
