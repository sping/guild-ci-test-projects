require 'rails_helper'

RSpec.describe User, type: :model do
  it 'should have a name' do
    expect(User.new(name: "Bob").name).to eq "Bob"
  end
end
