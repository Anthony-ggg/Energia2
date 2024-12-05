from app import create_app

if __name__ == "__main__":
    app = create_app()
    app.secrqet_key = 'tercero'
    app.config['SESSION_TYPE'] = 'filesystem'
    app.run(debug=True, host='0.0.0.0')