# How To Run

Create Virtual Environment:

``python3 -m venv .venv``

Activate Environment

``. .venv/bin/activate``

Install Requirements

``pip install -r requirements.txt``

Setup Database

``flask db upgrade``

Manually fetch the weather data of the past 3 days from the BOM

``python manually_fetch_data.py``

Run Server

``flask run``
