<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
        public function up(): void
        {
            Schema::create('titulos', function (Blueprint $table) 
            {
            // protected $fillable = ['ci','nombre','apellido_paterno','apellido_materno','titulo'];
                $table->id();
                $table->integer('ci');
                $table->string('nombre', 50);
                $table->string('apellido_paterno', 50);
                $table->string('apellido_materno', 50);
                $table->string('titulo', 50);
                $table->timestamps();
            });
        }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('titulos');
    }
};
