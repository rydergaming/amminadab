#-------------------------------------------------
#
# Project created by QtCreator 2015-11-26T12:53:21
#
#-------------------------------------------------

QT       += core gui

greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

TARGET = amminadab
TEMPLATE = app
CONFIG += c++11
INCLUDEPATH += -I/usr/include/link-grammar
INCLUDEPATH += -I/usr/include/boost
LIBS+= -L/usr/lib -llink-grammar
LIBS+= -L/usr/lib -lboost_date_time
LIBS += -L/usr/lib -lboost_system
LIBS += -pthread

SOURCES += main.cpp\
        ammiwindow.cpp\
        samu.cpp\
        nlp.cpp\
    samumain.cpp



HEADERS  += ammiwindow.h\
        samu.hpp\
        nlp.hpp\
        ql.hpp\
        qlc.h\
        net.hpp\
        qlc.cu \
    samumain.h

FORMS    += ammiwindow.ui
