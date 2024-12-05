from flask import Blueprint, abort, flash,request,render_template,redirect
import requests
import json

router = Blueprint('router', __name__)
@router.route('/pruebas')
def ho():
    r = requests.get('http://localhost:8099/myapp/person/list')
    # #print(type(r.json()))
    # #print(r.json())
    data = r.json()  
    return render_template('index.html', lista = data["data"]) 
   
@router.route('/')
def home():
    # r = requests.get('http://localhost:8099/myapp/person/list')
    # #print(type(r.json()))
    # #print(r.json())
    # data = r.json()  
   # return render_template('index.html', lista = data["data"]) 
    return render_template('fragmento/inicial/login.html') 

@router.route('/admin')

def admin():
    #r = requests.get('http://localhost:8099/myapp/person/list')
    #print(type(r.json()))
    #print(r.json())
    #data = r.json() 
    #print(r.json()) 
    return render_template('fragmento/inicial/admin.html') 


@router.route('/admin/person/list')
def list_person():
    r = requests.get('http://localhost:8099/myapp/person/list')
    data = r.json()
    print("AAAAAAAAAAAAAAAAAAAAAaa", data)
    return render_template('fragmento/persona/lista.html', lista = data["data"]) 

@router.route('/admin/person/registro')
def view_register_person():
    # r = requests.get('http://localhost:8099/myapp/person/listType')
    # data = r.json() 
    # print(r.json())

    return render_template('fragmento/persona/registro.html', lista = ['CEDULA', 'PASAPORTE', 'RUC'])  

@router.route('/admin/person/edit/<id>')
def view_edit_person(id):
    # r = requests.get('http://localhost:8099/myapp/person/listType')
    # data = r.json() 
    # print(r.json())
    r = requests.get('http://localhost:8099/myapp/person/get/'+id)
    return render_template('fragmento/persona/editar.html', lista = ['CEDULA', 'PASAPORTE', 'RUC'], persona = r.json())  



@router.route('/admin/person/save', methods=['POST'])
def save_persona():

    form = request.form
    #dataF=form.to_dict()
    dataF = {"tipo":form ['tipo'], "nombre":form['nombre'],"apellido":form['apellido'],"dni":form['dni'],"numCelular":form['numCelular'],"sexo":form['sexo'],"fechaNacimiento":form['fechaNacimiento']}
    print(form.to_dict())
    r = requests.post('http://localhost:8099/myapp/person/save', json=dataF)
    dat = r.json()
    print(dat)
    if r.status_code == 200:
        
        return redirect('/admin/person/list')
    else:
        
        return redirect('/admin/person/list')
    
# Buscar personas
@router.route('/admin/person/search/<criterio>/<texto>')
def view_buscar_person(criterio, texto):
    try:
        if criterio not in ["apellido", "dni"]:
            raise ValueError("Criterio inválido")
        url = f'http://localhost:8099/myapp/person/list/search/{texto}' if criterio == "apellido" else f'http://localhost:8099/myapp/person/list/search/ident/{texto}'
        response = requests.get(url)
        response.raise_for_status()
        data = response.json()
        lista_resultados = data.get("data", [])
        if lista_resultados:
            return render_template('fragmento/persona/lista.html', lista=lista_resultados, message=None)
        else:
            return render_template('fragmento/persona/lista.html', lista=[], message='No se encontraron resultados.')
    except ValueError as e:
        return render_template('fragmento/persona/lista.html', lista=[], message=str(e))
    except requests.exceptions.RequestException as e:
        print(f"Error al buscar persona: {str(e)}")
        return render_template('fragmento/persona/lista.html', lista=[], message='Error al conectar con el servidor.')



# Ordenar personas
@router.route('/admin/person/order/<atributo>/<tipo>/<algoritmo>')
def view_ordernar_inversionistas(atributo, tipo, algoritmo):
    try:
        if atributo not in ["apellido", "dni"]:
            raise ValueError("Atributo inválido")
        if tipo not in ["0", "1"]:
            raise ValueError("Tipo inválido, usa 0 para ascendente o 1 para descendente")
        url = f'http://localhost:8099/myapp/person/list/order/{atributo}/{tipo}/{algoritmo}'
        response = requests.get(url)
        response.raise_for_status()
        data = response.json()
        lista_ordenada = data.get("data", [])
        return render_template('fragmento/persona/lista.html', lista=lista_ordenada, message=None if lista_ordenada else 'No hay datos para ordenar.')
    except ValueError as e:
        return render_template('fragmento/persona/lista.html', lista=[], message=str(e))
    except requests.exceptions.RequestException as e:
        print(f"Error al ordenar personas: {str(e)}")
        return render_template('fragmento/persona/lista.html', lista=[], message='Error al mandar los criterios al ordenar')
  


@router.route('/admin/energia/list')
def list_energia():
    r = requests.get('http://localhost:8099/myapp/energia/list')
    data = r.json() 
    print("AAAAAAAAAAAAAAAAAAAAAaa", data)
    return render_template('fragmento/energia/listaE.html', lista = data["data"]) 

@router.route('/admin/energia/registro')
def view_registro():
    r = requests.get('http://localhost:8099/myapp/energia/list')
    data = r.json() 
    print("AAAAAAAAAAAAAAAAAAAAAaa", data)
    return render_template('fragmento/energia/registroE.html', lista = data["data"]) 

@router.route('/admin/energia/edit/<id>')
def view_edit_registro(id):
    r = requests.get("http://localhost:8099/myapp/energia/list")
    data = r.json() 
    r1 = requests.get("http://localhost:8099/myapp/energia/get/"+id)
    data1 = r1.json() 
    print("AAAAAAAAAAAAAAAAAAAAAaa", data1)
    if(r1.status_code == 200):
        return render_template('fragmento/energia/editarE.html', lista = data["data"], energia = data1["data"]) 
    else:
        flash(data1["data"],category= 'Error')
        return redirect('/admin/energia/list')




@router.route('/admin/energia/save', methods=['POST'])
def save_registro():
    headers = {'Content-Type': 'application/json'}
    form = request.form
    #dataF=form.to_dict()

    dataF = {"nombre":form ['nombre'], "inversion":form['inversion'],"tiempoVida":form['tiempoVida'],"fechaInicio":form['fechaInicio'],"fechaFin":form['fechaFin'],"capacidadDiaria":form['capacidadDiaria']}
    print(dataF)
    r = requests.post('http://localhost:8099/myapp/energia/save', json=dataF)
    dat = r.json()
    print(dat)
    if r.status_code == 200:
        
        return redirect('/admin/energia/list')
    else:
        
        return redirect('/admin/energia/list')
    


@router.route('/admin/energia/update', methods=['POST'])
def update_registro():
    headers = {'Content-Type': 'application/json'}
    form = request.form
    #dataF=form.to_dict()

    dataF = {"id":form ['id'],"nombre":form ['nombre'], "inversion":form['inversion'],"tiempoVida":form['tiempoVida'],"fechaInicio":form['fechaInicio'],"fechaFin":form['fechaFin'],"capacidadDiaria":form['capacidadDiaria']}
    print(dataF)
    r = requests.post('http://localhost:8099/myapp/energia/update', data=json.dumps(dataF), headers=headers)
    dat = r.json()
    print(dat)
    if r.status_code == 200:
        
        return redirect('/admin/energia/list')
    else:
        
        return redirect('/admin/energia/list')
    #return render_template('fragmento/energia/listaE.html', lista = dat["data"]) 


@router.route('/admin/energia/search/<criterio>/<tipoBusqueda>/<texto>')
def view_buscar_energia(criterio, tipoBusqueda, texto):
    url = 'http://localhost:8099/myapp/energia/list/search/'

    # Añadir el tipo de búsqueda al URL
    if criterio == "nombre":
        r = requests.get(url + f"{tipoBusqueda}/{texto}")
    elif criterio == "inversion":
        r = requests.get(url + f"inversion/{tipoBusqueda}/{texto}")
    elif criterio == "tiempoVida":
        r = requests.get(url + f"tiempoVida/{tipoBusqueda}/{texto}")

    # Obtener los datos de la respuesta
    data1 = r.json()

    # Verificar si la respuesta fue exitosa
    if r.status_code == 200:
        if type(data1["data"]) is dict:  # Si la respuesta contiene un solo proyecto
            lista = [data1["data"]]
            return render_template('fragmento/energia/listaE.html', lista=lista)
        else:  # Si la respuesta contiene varios proyectos
            return render_template('fragmento/energia/listaE.html', lista=data1["data"])
    else:  # Si la respuesta no fue exitosa, mostrar un mensaje de error
        return render_template('fragmento/energia/listaE.html', lista=[], message='No existe el elemento')

@router.route('/admin/energia/order/<atributo>/<tipo>/<algoritmo>')
def view_ordernar_Proyectos(atributo, tipo, algoritmo):
    try:
        if atributo not in ["nombre", "inversion", "tiempoVida"]:
            raise ValueError("Atributo inválido")
        if tipo not in ["0", "1"]:
            raise ValueError("Tipo inválido, usa 0 para ascendente o 1 para descendente")
        url = f'http://localhost:8099/myapp/energia/list/order/{atributo}/{tipo}/{algoritmo}'
        response = requests.get(url)
        response.raise_for_status()
        data = response.json()
        lista_ordenada = data.get("data", [])
        return render_template('fragmento/Energia/listaE.html', lista=lista_ordenada, message=None if lista_ordenada else 'No hay datos para ordenar.')
    except ValueError as e:
        return render_template('fragmento/Energia/listaE.html', lista=[], message=str(e))
    except requests.exceptions.RequestException as e:
        print(f"Error al ordenar personas: {str(e)}")
        return render_template('fragmento/Energia/listaE.html', lista=[], message='Error al mandar los criterios al ordenar')
  