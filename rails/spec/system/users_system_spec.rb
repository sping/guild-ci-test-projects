require "rails_helper"

RSpec.describe "Users management", :type => :system do

  it "lists users" do
    visit users_url

    assert_selector "h1", text: "Users"
  end

  it "updates the title on the edit page", js: true do
    user = create :user, name: "Me"

    visit "/users/#{User.first.id}/edit"

    assert_selector "h1", text: "Edit Me"
    fill_in "Name", :with => "Cool me"
    assert_selector "h1", text: "Edit Cool me"

    click_button "Submit"

    expect(page).to have_text("User Cool me")
  end
end
