<?php

namespace Database\Factories;

use Illuminate\Database\Eloquent\Factories\Factory;


/**
 * @extends \Illuminate\Database\Eloquent\Factories\Factory<\App\Models\Cliente>
 */
class ClienteFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array<string, mixed>
     */
    public function definition(): array
    {
        return [
            
    //protected $fillable = ['banco','nro_cuenta','ci', 'nombres', 'apellidos', 'saldo'];
            //banco solo puede llamarse ganadero
            'banco'=>$this->faker->randomElement(['ganadero']),
            'nro_cuenta'=>$this->faker->numberBetween(1,100),
            'ci'=>$this->faker->numberBetween(1,100),
            'nombres'=>$this->faker->name(),
            'apellidos'=>$this->faker->name(),
            'saldo'=>$this->faker->randomFloat(2,1,1000),
            
        ];
    }
}
