<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Departamento;

class DepartamentoController extends Controller
{
    public function index()
    {

        $departamentos = Departamento::all();
        return $departamentos;
    }

    public function show($id)
    {
        $departamento = Departamento::find($id);
        return $departamento;
        //como funciona 
    }
    public function store(Request $request)
    {
        // $departamento = new Departamento();
        // $departamento->nombre = $request->nombre;
        // $departamento->descripcion = $request->descripcion;
        // $departamento->codigo = $request->codigo;
        // $departamento->capital = $request->capital;
        // $departamento->superficie = $request->superficie;
        // $departamento->save();
        $departamento = Departamento::create($request->all());
        return $departamento;
    }
    public function update(Request $request, $id)
    {
        $departamento = Departamento::find($id);
        $departamento->update($request->all());
        return $departamento;
}
    public function destroy($id)
    {
        $departamento = Departamento::find($id);
        $departamento->delete();
        return $departamento;
    }
}
