<?php

namespace App\Http\Controllers;

use App\Models\Temperat;
use Illuminate\Http\Request;

class TemperatController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        return Temperat::all();
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(Request $request)
    {
        //
    }

    /**
     * Display the specified resource.
     */
    // public function show(Temperat $temperat)
    // {
    //     return Temperat::find($id);
    // }

    public function show($id)
    {
        $temperatura = Temperatura::find($id);

        if (!$temperatura) {
            return response()->json(['message' => 'Temperatura not found'], 404);
        }

        return response()->json($temperatura);
    }
    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, Temperat $temperat)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(Temperat $temperat)
    {
        //
    }
}
